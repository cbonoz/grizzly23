package com.solvays.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.quickbirdstudios.surveykit.AnswerFormat
import com.quickbirdstudios.surveykit.OrderedTask
import com.quickbirdstudios.surveykit.SurveyTheme
import com.quickbirdstudios.surveykit.steps.CompletionStep
import com.quickbirdstudios.surveykit.steps.InstructionStep
import com.quickbirdstudios.surveykit.steps.QuestionStep
import com.quickbirdstudios.surveykit.steps.Step
import com.quickbirdstudios.surveykit.survey.SurveyView
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
        val surveyQuestions = SurveyHelper.generateSurveyQuestions(getString(R.string.app_name))

        val steps: ArrayList<Step> = arrayListOf()
//        https://github.com/QuickBirdEng/SurveyKit#create-survey-steps
        steps.add(
            InstructionStep(
                title = "Begin survey",
                text = "Series of questions",
                buttonText = "Start"
            )
        )
        surveyQuestions.forEach {
            steps.add(
                QuestionStep(
                    title = "Question",
                    text = it,
                    answerFormat = AnswerFormat.SingleChoiceAnswerFormat(SurveyHelper.getAgreeChoices())
                )
            )
        }

        steps.add(
            CompletionStep(
                title = "Done",
                text = "Thanks for completing the survey",
                buttonText = "Get reward"
            )
        )

        val task = OrderedTask(steps = steps)
        val configuration = SurveyTheme(
            themeColorDark = ContextCompat.getColor(requireContext(), R.color.purple_500),
            themeColor = ContextCompat.getColor(
                requireContext(),
                androidx.appcompat.R.color.material_blue_grey_800
            ),
            textColor = ContextCompat.getColor(
                requireContext(),
                androidx.appcompat.R.color.material_grey_600
            )
        )
        surveyView.start(task, configuration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}