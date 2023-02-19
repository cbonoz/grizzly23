package com.solvays.ui.survey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.quickbirdstudios.surveykit.FinishReason
import com.quickbirdstudios.surveykit.OrderedTask
import com.quickbirdstudios.surveykit.SurveyTheme
import com.quickbirdstudios.surveykit.result.TaskResult
import com.quickbirdstudios.surveykit.steps.CompletionStep
import com.quickbirdstudios.surveykit.steps.InstructionStep
import com.quickbirdstudios.surveykit.steps.Step
import com.quickbirdstudios.surveykit.survey.SurveyView
import com.solvays.MainActivity
import com.solvays.R
import com.solvays.databinding.FragmentSurveyBinding
import com.solvays.util.SurveyHelper

class SurveyFragment : Fragment() {

    private lateinit var surveyView: SurveyView
    private var _binding: FragmentSurveyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSurveyBinding.inflate(inflater, container, false)
        val view: View = binding.root
        surveyView = view.findViewById(R.id.survey_view)

        setupQuestions()

        return view
    }

    private fun setupQuestions() {
        val appName = arguments?.getString("appName") ?: "UNKNOWN"
        val steps: ArrayList<Step> = arrayListOf()
//        https://github.com/QuickBirdEng/SurveyKit#create-survey-steps
        steps.add(
            InstructionStep(
                title = "Review the $appName app",
                text = "Complete a series of questions on $appName to earn a Solana reward",
                buttonText = "Start"
            )
        )

        steps.addAll(SurveyHelper.generateSurveyQuestionSteps(appName))

        steps.add(
            CompletionStep(
                title = "Done",
                text = "Thanks for completing the survey",
                buttonText = "Get reward"
            )
        )

        val task = OrderedTask(steps = steps)
        val configuration = SurveyTheme(
            themeColorDark = ContextCompat.getColor(
                requireContext(),
                androidx.appcompat.R.color.material_grey_300
            ),
            themeColor = ContextCompat.getColor(
                requireContext(),
                androidx.appcompat.R.color.material_blue_grey_800
            ),
            textColor = ContextCompat.getColor(
                requireContext(),
                androidx.appcompat.R.color.material_grey_600
            ),
        )
        surveyView.start(task, configuration)

        surveyView.onSurveyFinish = { taskResult: TaskResult, reason: FinishReason ->
            // TODO: add success case to remit payment
            if (reason == FinishReason.Discarded) {
                // route somewhere else
                (activity as MainActivity).navigateTo(R.id.nav_home)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}